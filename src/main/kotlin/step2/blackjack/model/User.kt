package step2.blackjack.model

import step2.blackjack.model.vo.Name

/**
 * 유저
 * */
class User(
    val name: Name,
    val cardList: CardList
) {

    companion object {
        fun from(name: String, cardList: List<Card> = emptyList()): User = User(Name.from(name), CardList.from(cardList))
    }
}
