package kotlindsl.model

data class Person(val name: String, val company: String?, val skills: List<Skill>?, val language: List<Language>?)
