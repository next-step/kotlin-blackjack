package step1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DslTest {

    @ParameterizedTest
    @ValueSource(strings = ["harris", "summer"])
    fun `introduce dsl로 객체 생성시 이름 필드 확인`(value: String){
        //given & when
        val person = introduce {
            name(value)
        }
        //then
        assertThat(person.name).isEqualTo(value)
    }

    @Test
    fun `introduce dsl로 객체 생성시 이름, 회사 필드 확인`(){
        //given && when
        val person = introduce {
            name("harris")
            company("ks")
        }
        //then
        assertAll(
            {assertThat(person.name).isEqualTo("harris")},
            {assertThat(person.company).isEqualTo("ks")}
        )
    }
}
