package br.com.aleson.daily.rewards.app.ui.activity

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.ui.base.BaseActivity

class LoginActivity : BaseActivity() {


    override fun getFragmentContainer(): Int {
        return R.id.nav_host
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}