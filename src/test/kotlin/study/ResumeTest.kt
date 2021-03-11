package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/*
// 설계할 때 이걸 먼저 디자인한다. 사용자 친화적인 것에 더 초점을 맞추기 때문이다.
introduce {
	name("박재성")
	company("우아한형제들") <- 이거슨 파라미터
	skills { <- 이거슨 펑션
		soft ("A passion for problem solving")
		soft ("Good communication skills")
		hard ("Kotlin")
	}
	languages {
		"Korean" level 5
		"English" level 3
	}
}
*/

class ResumeTest {

	@Test
	fun name() {
		val person: Person = introduce {
			name("황준오")
		}
		assertThat(person.name).isEqualTo("황준오")
	}

	@Test
	fun company() {
		val person: Person = introduce {
			name("황준오")
			company("준오컴퍼니")
		}
		assertThat(person.name).isEqualTo("황준오")
		assertThat(person.company).isEqualTo("준오컴퍼니")
	}

	@Test
	fun hard() {
		val person: Person = introduce {
			name("황준오")
			company("준오컴퍼니")
			skills {
				hard("Kotlin")
				soft("A passion for Eating")
				soft("soft is not hard!?")
			}
		}
		assertThat(person.name).isEqualTo("황준오")
		assertThat(person.company).isEqualTo("준오컴퍼니")
		assertThat(person.skills.toList()).containsExactlyInAnyOrder(
			Hard("Kotlin"),
			Soft("A passion for Eating"),
			Soft("soft is not hard!?")
		)
	}

	@Test
	fun languages() {
		val person: Person = introduce {
			name("황준오")
			company("준오컴퍼니")
			skills {
				hard("Kotlin")
				soft("A passion for Eating")
				soft("soft is not hard!?")
			}
			languages {
				"Korean" level 5
				"English" level 1
			}
		}
		assertThat(person.name).isEqualTo("황준오")
		assertThat(person.company).isEqualTo("준오컴퍼니")
		assertThat(person.skills.toList()).containsExactlyInAnyOrder(
			Hard("Kotlin"),
			Soft("A passion for Eating"),
			Soft("soft is not hard!?")
		)
		println(person.languages.toList())
		assertThat(person.languages.toList()).containsExactlyInAnyOrder(
			Language("Korean", 5),
			Language("English", 1)
		)
	}
}

fun introduce(initializer: Person.() -> Unit): Person { // initializer는 Person의 함수 중 인자는없고 리턴값은 Unit인 함수만 들어올 수 있다.
	return Person().apply(initializer)
// return Person().apply { initializer() } 를 간단하게 한 표현.
}

class Person {
	lateinit var name: String
	var company: String? = null
	lateinit var skills: Skills
	lateinit var languages: Languages

	fun name(name: String) {
		this.name = name
	}

	fun company(company: String) {
		this.company = company
	}

	fun skills(initializer: Skills.() -> Unit) { // initializer에는 Skills의 함수이며 인자는 없고 리턴타입이 Unit인 함수가 올거야!
		skills = Skills().apply(initializer)
	}

	fun languages(initializer: Languages.() -> Unit) {
		languages = Languages().apply(initializer)
	}
}

class Skills {
	private val skills: MutableList<Skill> = mutableListOf()

	fun hard(name: String) = skills.add(Hard(name)) // 이 hard 메서드는 Person에 들어갈 수도 있겠지만, Skills에 있는게 더 적절하다

	fun soft(name: String) = skills.add(Soft(name)) // 이 soft 메서드는 Person에 들어갈 수도 있겠지만, Skills에 있는게 더 적절하다

	fun toList(): List<Skill> {
		return skills
	}
}

sealed class Skill // 이 코틀린 파일에서만 상속할 수 있다. (즉, 외부에서는 상속 불가)
//abstract class Skill // <- 어디서나 Skill를 상속 할 수 있다

data class Hard(val name: String) : Skill()
data class Soft(val name: String) : Skill()

class Languages {
	private val languages: MutableList<Language> = mutableListOf()

	infix fun String.level(level: Int) = languages.add(Language(this, level))

	fun toList(): List<Language> {
		return languages
	}
}

data class Language(val name: String, val level: Int)


