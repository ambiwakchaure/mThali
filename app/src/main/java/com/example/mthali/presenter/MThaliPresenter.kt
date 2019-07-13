package com.example.mthali.presenter

import com.android.volley.TimeoutError
import com.android.volley.VolleyError
import com.example.mthali.model.IMThaliModel
import com.example.mthali.model.MThaliModel
import com.example.mthali.other.volley.VolleyReturnResponseGen
import com.example.mthali.view.IMThaliView
import java.util.ArrayList
import java.util.HashMap

class MThaliPresenter(iMThaliView : IMThaliView) : IMThaliPresenter
{
    var iMThaliView : IMThaliView
    var iMThaliModel : IMThaliModel
    init
    {
        this.iMThaliView = iMThaliView
        iMThaliModel = MThaliModel()
    }
    override fun doOperation(params: Array<String>, purpose: String)
    {
        val response = ArrayList<Any>()
        val return_response = HashMap<String, List<Any>>()

        iMThaliModel.doOperation(object : VolleyReturnResponseGen {
            override fun onSuccessnResponse(return_object: Map<String, List<Any>>) {
                iMThaliView.updateUi(return_object,purpose)
            }

            override fun onErrorResponse(error: VolleyError) {

                if(error is TimeoutError)
                {
                    response.add("TimeoutError")
                    return_response.put(purpose,response)
                    iMThaliView.updateUi(return_response,purpose)
                }
                else
                {
                    response.add(""+error)
                    return_response.put(purpose,response)
                    iMThaliView.updateUi(return_response,purpose)
                }
            }
        },params,purpose)
    }
}