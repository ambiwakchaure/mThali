package com.example.mthali.model

import com.example.mthali.other.volley.VolleyReturnResponseGen

interface IMThaliModel {
    fun doOperation(volleyReturnResponse: VolleyReturnResponseGen, params : Array<String>, purpose : String)
}