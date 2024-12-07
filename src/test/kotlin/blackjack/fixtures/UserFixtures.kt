package blackjack.fixtures

import blackjack.domain.Cards
import blackjack.domain.User

fun createUsers(names: List<String> = listOf("홍길동", "홍길덩")): List<User> {
    return names.map { User(it, Cards(emptyList())) }
}
