package com.joancolmenerodev.githubclient.feature.userInfo.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.joancolmenerodev.githubclient.R
import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
import com.joancolmenerodev.githubclient.feature.userInfo.presenter.UserInfoContract
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), UserInfoContract.View, KodeinAware {

    override val kodein by kodein()
    private val presenter: UserInfoContract.Presenter by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGithubUser.setOnClickListener {
            presenter.findUser(et_githubUser.text.toString())
        }


    }

    override fun showUserInfo(githubUserResponse: GithubUserResponse) {
        System.out.println(githubUserResponse.toString())
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(linear_parent_main_activity, errorMessage, Snackbar.LENGTH_SHORT).show()
    }

    override fun showProgressBar(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }


}
