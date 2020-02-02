package br.com.aleson.daily.rewards.app.feature.login.view.activity

import android.os.Bundle
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.base.BaseActivity

class LoginActivity : BaseActivity() {


    override fun getFragmentContainer(): Int {
        return R.id.nav_host
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}