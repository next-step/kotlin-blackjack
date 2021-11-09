package dsl

import dsl.languages.Languages
import dsl.skills.Skills

data class Person(val name: String?, val company: String?, val skills: Skills?, val language: Languages?)
// {
//     lateinit var name: String
//     lateinit var company: String
//     lateinit var skills: Skills
//     lateinit var language: Languages
//
//     fun name(name: String) {
//         this.name = name
//     }
//
//     fun company(company: String) {
//         this.company = company
//     }
//
//     fun skills(init: Skills.() -> Unit) {
//         this.skills = Skills().apply(init)
//     }
//
//     fun languages(init: Languages.() -> Unit) {
//         this.language = Languages().apply(init)
//     }
// }
