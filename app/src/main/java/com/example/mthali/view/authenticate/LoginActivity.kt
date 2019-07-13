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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), IMThaliView{

    lateinit var progressDialog : ProgressDialog
    lateinit var iMThaliPresenter : IMThaliPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        iMThaliPresenter = MThaliPresenter(this)
        setClickListner()
    }

    private fun setClickListner() {

        signin_btn.setOnClickListener {
            if(T.isNetworkAvailable())
            {
                validateInputs()
            }
            else
            {
                T.s(main_layout,Constants.NETWORK)
            }
        }
        signup_btn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }

    private fun validateInputs() {

        if(!T.validateEditext(main_layout,username_edt,"Oops ! enter username"))
        {
            return
        }
        if(!T.validateEditext(main_layout,password_edt,"Oops ! enter password"))
        {
            return
        }
        proceedLogin()
    }

    private fun proceedLogin() {

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Signin, please wait...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        val params = arrayOf(
            Constants.USERNAME+"#"+username_edt.text.toString(),
            Constants.PASSWORD+"#"+password_edt.text.toString()
        )
        iMThaliPresenter.doOperation(params, Constants.LOGIN_USER)
    }
    override fun updateUi(response: Map<String, List<Any>>, purpose: String) {
        progressDialog.dismiss()
    }
}
