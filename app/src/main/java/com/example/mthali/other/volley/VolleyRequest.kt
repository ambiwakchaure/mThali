package com.example.mthali.other.volley

import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.mthali.other.MyApplication
import java.lang.Exception
import java.util.HashMap

class VolleyRequest
{
    companion object{

        fun makeVolleyRequest(
            volleyReturnResponse : VolleyReturnResponse,
            url : String,
            parameters : Array<String>)
        {


            try
            {
                val stringRequest = object : StringRequest
                    (
                    Method.POST,
                    url,
                    Response.Listener<String>
                    {
                            response ->
                            //return volley response
                            volleyReturnResponse.returnResult(response)

                    },
                    object : Response.ErrorListener
                    {
                        override fun onErrorResponse(volleyError: VolleyError)
                        {
                            //return volley error
                            volleyReturnResponse.returnVolleyError(volleyError)

                        }
                    }
                )
                {

                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String>
                    {
                        val params = HashMap<String, String>()
                        for (i in 0.. parameters.size - 1)
                        {
                            var param = parameters.get(i).split("#")
                            params.put(param.get(0), param.get(1))
                        }
                        return params
                    }
                }
                //adding request to queue
                MyApplication.instance!!.addToRequestQueue(stringRequest)
            }
            catch (e : Exception)
            {
            }
        }
    }
}