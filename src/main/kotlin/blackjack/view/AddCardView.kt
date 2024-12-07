package blackjack.view

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.Player

fun addCardView(blackJack: BlackJack) {
    val players = blackJack.players.players
    println()
    players.forEach { addCardViewByPlayer(it, blackJack.cardDeck) }
}

fun addCardViewByPlayer(
    player: Player,
    cardDeck: CardDeck,
) {
    var inputYesOrNo: String? = "y"
    while (inputYesOrNo == "y") {
        inputYesOrNo = addCardReturnYesOrNo(player, cardDeck)
    }
}

fun addCardReturnYesOrNo(
    player: Player,
    cardDeck: CardDeck,
): String {
    println(player.playerName.name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

    val inputYesOrNo = readlnOrNull()
    requireNotNull(inputYesOrNo) { "어떠한 값도 입력하지 않았습니다" }
    require(inputYesOrNo == "y" || inputYesOrNo == "n") { "y 또는 n을 올바르게 입력되지 않았습니다" }

    if (inputYesOrNo == "y") {
        player.addCard(cardDeck.drawCard())
        println(player.mutableCards.cardsToString())
    }

    return inputYesOrNo
}
