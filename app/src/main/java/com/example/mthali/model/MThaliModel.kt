package com.example.mthali.model

import com.android.volley.VolleyError
import com.example.mthali.other.Constants
import com.example.mthali.other.volley.VolleyRequest
import com.example.mthali.other.volley.VolleyReturnResponse
import com.example.mthali.other.volley.VolleyReturnResponseGen
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

class MThaliModel : IMThaliModel
{
    override fun doOperation(volleyReturnResponse: VolleyReturnResponseGen, params: Array<String>, purpose: String)
    {
        var url = ""
        if (purpose.equals(Constants.LOGIN_USER))
        {
            url = Constants.LOGIN_USER
        }
        else if (purpose.equals(Constants.REGISTER_USER))
        {
            url = Constants.REGISTER_USER
        }
        try
        {
            VolleyRequest.makeVolleyRequest(object : VolleyReturnResponse
            {
                override fun returnResult(response: String)
                {
                    if (purpose.equals(Constants.LOGIN_USER))
                    {
                        //LOGIN_USER
                        var registerResponse = parseLoginResponse(response);
                        volleyReturnResponse.onSuccessnResponse(registerResponse)
                    }
                    else if (purpose.equals(Constants.REGISTER_USER))
                    {
                        //REGISTER_USER
                        var registerResponse = parseRegisterResponse(response);
                        volleyReturnResponse.onSuccessnResponse(registerResponse)
                    }
                }
                override fun returnVolleyError(error: VolleyError) {
                    volleyReturnResponse.onErrorResponse(error)
                }
            }, url, params);
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun parseRegisterResponse(response: String): Map<String, List<Any>> {

        val returnCode = ArrayList<Any>()
        val return_response = HashMap<String, List<Any>>()
        return  return_response
    }
    private fun parseLoginResponse(response: String): Map<String, List<Any>> {

        val returnCode = ArrayList<Any>()
        val return_response = HashMap<String, List<Any>>()
        return  return_response
    }
}