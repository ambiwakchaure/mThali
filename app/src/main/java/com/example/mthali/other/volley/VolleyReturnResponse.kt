package com.example.mthali.other.volley

import com.android.volley.VolleyError

interface VolleyReturnResponse {
    fun returnResult(response : String)
    fun returnVolleyError(error : VolleyError)
}