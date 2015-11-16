package com.yiming.jianyue.utils;

import com.yiming.jianyue.data.Data;
import com.yiming.jianyue.data.Result;
import com.yiming.jianyue.data.WeixinArticles;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * 项目名称：jianyue
 * 类描述：
 * 创建人：wengyiming
 * 创建时间：15/11/16 下午10:34
 * 修改人：wengyiming
 * 修改时间：15/11/16 下午10:34
 * 修改备注：
 */
public interface Api {
    @GET(Config.WEIXIN)
    Observable<Result<Data<WeixinArticles>>> getArticleList(@Query("pno") int pno, @Query("ps") int ps
            , @Query("dtype") String dataType, @Query("key") String key);
}
