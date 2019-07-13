package com.example.mthali.view.authenticate

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.mthali.R
import com.example.mthali.other.Constants
import com.example.mthali.other.T
import com.example.mthali.presenter.IMThaliPresenter
import com.example.mthali.presenter.MThaliPresenter
import com.example.mthali.view.IMThaliView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), IMThaliView {

    lateinit var progressDialog : ProgressDialog
    lateinit var iMThaliPresenter : IMThaliPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        iMThaliPresenter = MThaliPresenter(this)

        setClickListner()
    }
    private fun setClickListner() {

        submit_btn.setOnClickListener {
            if(T.isNetworkAvailable())
            {
                validateInputs()
            }
            else
            {
                T.s(main_layout, Constants.NETWORK)
            }
        }

    }
    private fun validateInputs() {

        if(!T.validateEditext(main_layout,mobile_edt,"Oops ! enter mobile"))
        {
            return
        }
        if(!T.validateEditext(main_layout,password_edt,"Oops ! enter password"))
        {
            return
        }
        if(!T.validateEditext(main_layout,confpassword_edt,"Oops ! enter confirm password"))
        {
            return
        }
        if(!T.validatePasswordConfirm(main_layout,password_edt,confpassword_edt,"Oops ! Password and confirm password should be same"))
        {
            return
        }
        proceedToRegister()
    }

    private fun proceedToRegister() {

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Registering, please wait...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val params = arrayOf(
            "mobile#"+mobile_edt.text.toString(),
            "password#"+password_edt.text.toString()
        )
        iMThaliPresenter.doOperation(params, Constants.REGISTER_USER)
    }
    override fun updateUi(response: Map<String, List<Any>>, purpose: String) {
        progressDialog.dismiss()
    }
}
