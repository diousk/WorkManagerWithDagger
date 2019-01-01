package com.example.chenweiming.mykktapplication.api

import com.example.chenweiming.mykktapplication.api.response.PageResponse
import com.example.chenweiming.mykktapplication.api.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by david.chen@soocii.me on 2018/11/15.
 */
interface WikiApiService {
    @GET("api.php?action=query&format=json&list=search")
    fun hitCountCheck(@Query("srsearch") srsearch: String): Single<SearchResponse.Result>

    @GET("api.php?action=query&format=json&list=search")
    fun hitCountCheckRaw(@Query("srsearch") srsearch: String): Single<ResponseBody>

    @GET("api.php?action=query&prop=info|pageimages&pithumbsize=500&format=json&inprop=url")
    fun getPage(@Query("pageids") pageId: Int): Single<PageResponse.Result>

    @GET("api.php?action=query&prop=info|pageimages&pithumbsize=500&format=json&inprop=url")
    fun getPageRaw(@Query("pageids") pageId: Int): Single<ResponseBody>
}