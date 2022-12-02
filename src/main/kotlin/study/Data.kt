package study

data class Person(val name: String, val company: String? = "", val skills: Skills, val language: Languages)
data class Skills(val softs: List<String>, val hards: List<String>)
data class Level(val language: String, val level: Int)
data class Languages(val levels: List<Level>)
