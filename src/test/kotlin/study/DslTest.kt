//package study
//
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.params.ParameterizedTest
//import org.junit.jupiter.params.provider.ValueSource
//
//class DslTest {
//    @ParameterizedTest
//    @ValueSource(strings = ["팡, 팡팡"])
//    fun introduce(name: String) {
//        val person: Person = introduce {
//            name(name)
//        }
//
//        assertThat(person.name).isEqualTo(name)
//    }
//
//    @Test
//    fun company() {
//        val person = introduce {
//            name("팡")
//            company("깎오")
//        }
//
//        assertThat(person.name).isEqualTo("팡")
//        assertThat(person.company).isEqualTo("깍오")
//
//    }
//}
//
//fun introduce(block: PersonBuilder.() -> Unit): Person {
//    return PersonBuilder().apply { this.block() }.build()
//}
//
//class PersonBuilder {
//    private var name: String = ""
//    private var company: String = ""
//    fun name(value: String) {
//        name = value
//    }
//
//    fun company(value: String) {
//        company = value
//    }
//
//    fun build(): Person {
//        return Person(name, company)
//    }
//}
//
//data class Person(val name: String, val company: String)
