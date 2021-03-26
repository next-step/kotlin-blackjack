package blackjack.view

import blackjack.domain.MatchResult
import blackjack.domain.Result
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player

fun printStartMessage(dealer: Dealer, players: List<Player>) {
    println("딜러와 ${players.map { it.name.value }.joinToString { it }}에게 2장의 카드를 나누었습니다.")
    printDealerCards(dealer)
    printPlayersCards(players)
}

fun printDealerTakeCardMassage() {
    println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
}

private fun printDealerCards(dealer: Dealer) {
    val card = dealer.state.cards.elements[0]
    println("${dealer.name.value} 카드: ${mapping(card.denomination)}${mapping(card.suit)}")
}

private fun printPlayersCards(players: List<Player>) {
    players.forEach { printPlayerCards(it) }
}

fun printPlayerCards(player: Player) {
    println(
        "${player.name.value} 카드: ${player.state.cards.elements.joinToString { "${mapping(it.denomination)}${mapping(it.suit)}" }}"
    )
}

fun printParticipantsResult(participants: List<Participant>) {
    println()
    participants.forEach { printPlayerResult(it) }
}

private fun printPlayerResult(participant: Participant) {
    println(
        "${participant.name.value} 카드: ${participant.state.cards.elements.joinToString { "${mapping(it.denomination)}${mapping(it.suit)}" }} - 결과: ${participant.score.value}"
    )
}

private fun mapping(denomination: Denomination) = when (denomination) {
    Denomination.ACE -> "A"
    Denomination.TWO -> "2"
    Denomination.THREE -> "3"
    Denomination.FOUR -> "4"
    Denomination.FIVE -> "5"
    Denomination.SIX -> "6"
    Denomination.SEVEN -> "7"
    Denomination.EIGHT -> "8"
    Denomination.NINE -> "9"
    Denomination.TEN -> "10"
    Denomination.JACK -> "J"
    Denomination.QUEEN -> "Q"
    Denomination.KING -> "K"
}

private fun mapping(suit: Suit) = when (suit) {
    Suit.SPADE -> "스페이드"
    Suit.HEART -> "하트"
    Suit.CLUB -> "클럽"
    Suit.DIAMOND -> "다이아몬드"
}

fun printResult(result: Result) {
    println()
    println("## 최종 승패")
    result.elements.forEach { (player, matchResult) -> println("${player.name.value}: ${mapping(matchResult)}") }
}

fun mapping(matchResult: MatchResult) = when (matchResult) {
    MatchResult.WIN -> "승"
    MatchResult.LOSE -> "패"
    else -> "무"
}
