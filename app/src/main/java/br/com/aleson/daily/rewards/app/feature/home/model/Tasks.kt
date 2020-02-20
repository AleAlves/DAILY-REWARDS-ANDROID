package br.com.aleson.daily.rewards.app.feature.home.model

data class Tasks(
    var _id: String,
    var title: String,
    var description: String,
    var goal: Int,
    var groupId: String,
    var recurrence: Recurrence
)

data class Recurrence(var daily: Boolean, var weekly: Weekly, var monthly: Monthly)

data class Weekly(var weekly: Boolean, var dayOfWeek: Int)

data class Monthly(var weekly: Boolean, var dayOfMonth: Int)
