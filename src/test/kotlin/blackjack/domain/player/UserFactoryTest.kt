package blackjack.domain.player

import blackjack.domain.BettingMoney
import blackjack.domain.createUserNames
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class UserFactoryTest {
    @DisplayName("유저 이름 생성")
    @Test
    fun createUserNames() {
        val names = "pobi, jason"

        val userNames = UserFactory.createUserNames(names)

        assertThat(userNames.userNames).isEqualTo(createUserNames("pobi", "jason").userNames)
    }

    @DisplayName("유저 생성")
    @Test
    fun createUsers() {
        val userNames = createUserNames("pobi", "jason")
        val bettingMoneys = listOf(BettingMoney("0"), BettingMoney("0"))

        val users = UserFactory.createUsers(userNames, bettingMoneys)

        assertAll(
            { assertThat(users.users.size).isEqualTo(3) },
            {
                assertThat(users.users.map { it.userName })
                    .contains(UserName("딜러"), UserName("pobi"), UserName("jason"))
            }
        )
    }
}
