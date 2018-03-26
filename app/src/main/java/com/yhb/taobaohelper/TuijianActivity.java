package com.yhb.taobaohelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.yhb.taobaohelper.model.CouponModel;
import com.yhb.taobaohelper.model.ProductExtraModel;
import com.yhb.taobaohelper.model.ProductModel;
import com.yhb.taobaohelper.utils.BmobUtil;
import com.yhb.taobaohelper.utils.ProductListAdapter;
import com.yhb.taobaohelper.callback.SearchCallback;
import com.yhb.taobaohelper.utils.TaoBaoHelper;
import com.yhb.taobaohelper.utils.TextUtil;

import java.io.IOException;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TuijianActivity extends BaseActivity {
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tuijian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = mViewHolder.get(R.id.recylerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final EditText et_keyword = mViewHolder.get(R.id.et_keyword);
        mViewHolder.get(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_keyword.setText("");
            }
        });
        final ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        mViewHolder.get(R.id.btn_paster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 将剪贴板上的文字信息取出来放到文本框中
                 */
                ClipData mClipData = mClipboardManager.getPrimaryClip();
                ClipData.Item item = mClipData.getItemAt(0);
                if (item == null) {
                    return;
                }
                et_keyword.setText(item.getText().toString());
            }
        });
        mViewHolder.get(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = et_keyword.getText().toString();
                if (keyword.length() == 0) {
                    toastLong("关键字不能为空");
                    return;
                }

                if (TextUtil.isNumeric(keyword)) {
                    keyword = "https://item.taobao.com/item.htm?id=" + keyword;
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                TaoBaoHelper.search(keyword, new SearchCallback() {
                    @Override
                    public void response(List<ProductModel> data, boolean isOK) {
                        if (!isOK) {
                            toastLong("查询出错");
                            return;
                        }
                        if (data == null || data.size() == 0) {
                            toastLong("没有该宝贝信息");
                            return;
                        }
                        final ProductListAdapter adapter = new ProductListAdapter(getLayoutInflater(), data);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setAdapter(adapter);
                            }
                        });
                    }
                });
            }
        });

        mViewHolder.get(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ProductListAdapter.selectedModel == null) {
                    toastLong("先选择一个宝贝");
                    return;
                }
                BmobQuery<ProductModel> query = new BmobQuery<ProductModel>();

                query.addWhereEqualTo("auctionId", ProductListAdapter.selectedModel.getAuctionId());

                query.setLimit(1);
                query.findObjects(new FindListener<ProductModel>() {
                    @Override
                    public void done(List<ProductModel> object, BmobException e) {
                        if (e == null) {
                            if (object == null || object.size() == 0) {
                                addNew();
                            } else {
                                ProductModel p2 = object.get(0);
                                p2.setObjectId(p2.getObjectId());
                                p2.delete(new UpdateListener() {

                                    @Override
                                    public void done(BmobException e) {
                                        addNew();
                                    }

                                });
                            }
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });


            }
        });
    }


    private void addNew() {
        TaoBaoHelper.generateCoupon(ProductListAdapter.selectedModel.getAuctionId(), new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                toastLong("复制失败，请稍后重试");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                if (response.request().url().host().equals("pub.alimama.com")) {
                    CouponModel couponModel = new Gson().fromJson(json, CouponModel.class);
                    ProductExtraModel productExtraModel = new ProductExtraModel();
                    if (couponModel.isOk()) {
                        productExtraModel.setAuctionId(ProductListAdapter.selectedModel.getAuctionId());
                        productExtraModel.setCouponLink(getCouponLink(couponModel));
                        productExtraModel.setCouponLinkTaoToken(getCouponLinkTaoToken(couponModel));
                        BmobUtil.updateProductLink(productExtraModel);//保存淘口令

                        ProductListAdapter.selectedModel.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    toastLong("添加成功");
                                } else {
                                    toastLong("添加失败:" + e.getMessage());
                                }
                            }
                        });


                    }
                } else {
                    toastLong("淘宝登录失效，请重新登录");

                }

            }
        });
    }

    private String getCouponLink(CouponModel result) {
        return result.getData().getCouponLink() != null &&
                result.getData().getCouponLink().length() > 0 ?
                result.getData().getCouponLink() : result.getData().getClickUrl();

    }

    private String getCouponLinkTaoToken(CouponModel result) {
        return result.getData().getCouponLinkTaoToken() != null &&
                result.getData().getCouponLinkTaoToken().length() > 0 ?
                result.getData().getCouponLinkTaoToken() :
                result.getData().getTaoToken();
    }
}
