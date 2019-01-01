package com.example.chenweiming.mykktapplication.api.response

import com.squareup.moshi.JsonClass

/**
 * Created by david.chen@soocii.me on 2018/11/15.
 */
object PageResponse {
    data class Result(val query: Query)

    data class Query(val pages: Map<String, Page>)
    data class Thumbnail(val source: String, val width: Int, val height: Int)

    @JsonClass(generateAdapter = true)
    data class Page(var pageid: Int, val title: String = "", val fullurl: String, val touched: String, val thumbnail: Thumbnail?, val pageimage: String = ""):Comparable<Page> {
        override fun compareTo(other: Page): Int {
            return this.title.compareTo(other.title)
        }
    }
}