package com.example.chenweiming.mykktapplication.api.response

/**
 * Created by david.chen@soocii.me on 2018/11/15.
 */
object SearchResponse {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo, val search: List<Record>)
    data class SearchInfo(val totalhits: Int)
    data class Record(val pageid: Int, val title: String, val snippet: String)
}