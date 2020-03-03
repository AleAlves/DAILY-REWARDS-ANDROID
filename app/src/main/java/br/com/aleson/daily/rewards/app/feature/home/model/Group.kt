package br.com.aleson.daily.rewards.app.feature.home.model

data class Group(
    var _id: String,
    var allowedMembers: Int,
    var ownerUid: String,
    var title: String,
    var description: String,
    var picture: String,
    var members: List<Members>,
    var tasksIds: List<TasksId>
)

data class Members(
    var uid: String,
    var name: String
)

data class TasksId(
    var id: String,
    var title: String
)