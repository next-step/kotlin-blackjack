package game

import card.Cards
import card.Deck
import card.Hand
import card.TwoCards
import player.Name
import player.Player

sealed class BlackJackGame private constructor(
    open val players: List<Player>,
    open val deck: Deck,
) {
    class Start(
        override val players: List<Player>,
        override val deck: Deck = Deck.of(),
    ) : BlackJackGame(players, deck) {
        init {
            require(players.isNotEmpty()) { "플레이어는 한명이상 존재해야합니다." }
        }

        fun drawFirstTwoCards(): Play =
            players.map { player ->
                check(player is Player.Start) { "첫 시작은 빈손이어야합니다." }
                player.addTwoCard(TwoCards(deck.draw(), deck.draw()))
            }.let {
                Play(it, deck)
            }

        companion object {
            fun startGame(playerNames: List<String>) =
                Start(playerNames.map { Player.Start(Name(it), Hand(Cards())) })
        }
    }

    class Play(
        override val players: List<Player>,
        override val deck: Deck,
    ) : BlackJackGame(players, deck) {
        init {
            players.forEach {
                require(it is Player.OnGoing) { "onGoing 만 Play 상태로 넘어갈 수 있습니다." }
                require(it.hand.cards.values.size == 2) { "카드는 두장 씩 가지고 있어야 합니다." }
            }
        }

        fun draw(player: Player.OnGoing): Play =
            Play(getPlayersWithoutOnePerson(player.name) + player.addCard(deck.draw()), deck)

        fun toStay(targetPlayer: Player.OnGoing): Play =
            Play(getPlayersWithoutOnePerson(targetPlayer.name) + targetPlayer.toStay(), deck)

        fun isEnd(): Boolean = players.any { it is Player.OnGoing }.not()

        fun finish(): End {
            check(isEnd()) { "게임이 아직 종료되지 않았습니다." }
            return End(players, deck)
        }

        fun getOnGoingPlayers(): List<Player.OnGoing> = players.filterIsInstance<Player.OnGoing>()

        fun getPlayerByName(name: Name): Player =
            players.firstOrNull {
                it.name == name
            } ?: throw IllegalArgumentException("존재하지 않는 플레이어 입니다.")

        private fun getPlayersWithoutOnePerson(targetPlayerName: Name): List<Player> =
            players.filter { player -> player.name.value != targetPlayerName.value }
    }

    class End(
        override val players: List<Player>,
        override val deck: Deck,
    ) : BlackJackGame(players, deck) {
        fun getResult(): BlackJackResult =
            BlackJackResult(players)
    }
}
