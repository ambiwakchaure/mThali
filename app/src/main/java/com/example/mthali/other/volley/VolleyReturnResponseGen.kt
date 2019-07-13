package com.example.mthali.other.volley

import com.android.volley.VolleyError

interface VolleyReturnResponseGen {

    fun onSuccessnResponse(return_object: Map<String, List<Any>>)
    fun onErrorResponse(error: VolleyError)
}