package br.com.aleson.daily.rewards.app.feature.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.aleson.daily.rewards.app.R
import br.com.aleson.daily.rewards.app.core.ui.BaseRecyclerListener
import br.com.aleson.daily.rewards.app.feature.login.model.User

class HomeActivity : AppCompatActivity(), BaseRecyclerListener<User> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onClickListener(data: User, v: View, code: Int) {
    }
}
