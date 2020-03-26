package br.com.aleson.daily.rewards.app.feature.home.view.viewstate

import br.com.aleson.daily.rewards.app.feature.home.model.Group
import br.com.aleson.daily.rewards.app.feature.home.model.Tasks

sealed class HomeViewEvent {

    data class OnLoadTasks(val tasks: List<Tasks>?) : HomeViewEvent()

    data class OnLoadGroups(val groups: List<Group>?) : HomeViewEvent()

}