package com.kayakstudio.smalacomponents.swipefoldercard

object PlayerGenerator {

     fun generatePlayers(): List<Player> {
        val players = ArrayList<Player>()
        val player1 = Player().apply {
            name = "Malik"
            nationality = "French"
            club = "Fc Nantes"
            rating = "10"
            age = "27"
        }
        val player2 = Player().apply {
            name = "Marc"
            nationality = "French"
            club = "Olympique de Marseille"
            rating = "7"
            age = "30"
        }
        val player3 = Player().apply {
            name = "Loic"
            nationality = "French"
            club = "Olympique de Marseille"
            rating = "8"
            age = "29"
        }
        val player4 = Player().apply {
            name = "Johannes"
            nationality = "German"
            club = "Bayern munich"
            rating = "10"
            age = "28"
        }
        players.add(player1)
        players.add(player2)
        players.add(player3)
        players.add(player4)
        return players
    }
}