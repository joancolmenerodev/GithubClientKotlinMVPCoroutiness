package com.joancolmenerodev.githubclient.feature.userInfo.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.joancolmenerodev.githubclient.R
import com.joancolmenerodev.githubclient.feature.userInfo.model.GithubUserResponse
import com.joancolmenerodev.githubclient.feature.userInfo.presenter.UserInfoContract
import com.joancolmenerodev.githubclient.feature.userdetails.ui.UserGithubDetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_github_item.*
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
        layout_github_user_data.setOnClickListener {
            presenter.onItemClicked(tv_githubuser_username.text.toString())
        }
    }

    override fun showUserInfo(githubUserResponse: GithubUserResponse) {
        layout_github_user_data.visibility = View.VISIBLE
        layout_github_user_not_found.visibility = View.GONE
        Glide.with(this)
            .load(githubUserResponse.avatar_url)
            .into(iv_githubuser_picture)
        tv_githubuser_username.text = githubUserResponse.login
        tv_githubuser_location.text = githubUserResponse.location
        tv_githubuser_followers.text =
            String.format(resources.getString(R.string.github_user_followers), githubUserResponse.followers)
        tv_githubuser_following.text =
            String.format(resources.getString(R.string.github_user_following), githubUserResponse.following)

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

    override fun navigateToDetails(login: String) {
        val intent = Intent(
            this@MainActivity,
            UserGithubDetailActivity::class.java
        )
        val bundle = Bundle().also {
            it.putString("github_user_login", login)
        }
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun setUserErrorLayoutVisible() {
        layout_github_user_not_found.visibility = View.VISIBLE
        layout_github_user_data.visibility = View.GONE
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
