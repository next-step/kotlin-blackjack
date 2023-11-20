package blackjack

import blackjack.domain.card.Deck
import blackjack.domain.card.HandCards
import blackjack.domain.player.Hand
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState

class Blackjack

fun main() {
    val deck = Deck.fullDeck()
    deck.shuffle()

    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
    val playerNames = readln().split(",").map { it.trim() }
    val players = playerNames.map { Player(it, Hand(HandCards(mutableListOf(deck.draw(), deck.draw())))) }

    println("${players.joinToString(",") { it.name }} 에게 2 장의 카드 나누었습니다.")

    players.forEach { player ->
        println("${player.name}: ${player.hand.handCards}")
    }

    players.forEach { player ->
        while (player.state == PlayerState.Hit) {
            println("${player.name}은 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            val command = readln().trim()
            if (command == "y") {
                player.hit()
                val card = deck.draw()
                player.addCard(card)
                println("${player.name}: ${player.hand.handCards}")
            } else {
                player.stay()
            }
        }
        println("${player.name}: ${player.hand.handCards}")
        println("${player.state}")
    }

    players.forEach { player ->
        println("${player.name}: ${player.hand}")
    }
}
