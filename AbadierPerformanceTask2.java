/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.abadierperformancetask2;

import java.util.Scanner;

/**
 *
 * @author ron.g.abadier
 */
public class AbadierPerformanceTask2 {

    public static void main(String[] args) {
        String playerOneMove, playerTwoMove, rock = "r", paper = "p", scissors = "s";
        int guessCount = 1;
        boolean draw = false, playerOneWin = false;
        
        System.out.println("Welcome, players, to Cows and Bulls!");
        System.out.println("\nMechanics:\nThe winner in Rock, Paper, Scissors will input a 4-digit number that the other player will guess.\nThe player is only limited to 10 guesses and will have to guess the secret number by then to win the game.\nBulls are numbers in the guess and the target have in common, and in the correct position;\nCows are numbers in the guess and the target have in common, but in the wrong position.");
        System.out.println("\nLet us begin the game!");
        
        do {
            System.out.println("\nInput (r) Rock; (p) Paper; (s) Scissors");
            Scanner scn = new Scanner(System.in);
            System.out.print("Player 1: ");
            playerOneMove = scn.nextLine();
            System.out.print("Player 2: ");
            playerTwoMove = scn.nextLine();
            playerMove(playerOneMove, playerTwoMove);

            if (playerOneMove.equals(playerTwoMove)) {
                draw = true;
                System.out.println("\nIt's a draw! Please try again.");
            } else if (playerOneMove.equals(rock)){
                draw = false;
                playerOneWin = !playerTwoMove.equals(paper);
            } else if (playerOneMove.equals(paper)){
                draw = false;
                playerOneWin = !playerTwoMove.equals(scissors);
            } else if (playerOneMove.equals(scissors)){
                draw = false;
                playerOneWin = !playerTwoMove.equals(rock);
            }
        } while(draw);
        
        int[] secret = playerWin(playerOneWin);
        
        while (guessCount <= 10) {
            System.out.print("\nGuess #" + guessCount + ": ");
            int bulls = hintCount(secret);
            guessCount++;
            
            if (bulls == 4) {
                System.out.println("\nCongratulations! You have discovered the secret code.");
                break;
            }
            
            if (guessCount == 11) {
                System.out.println("\nGame over! Try again next time.");
            }
        }
    }
    
    static void playerMove(String playerOne, String playerTwo) {
        switch(playerOne){
            case "r" -> System.out.print("\nRock vs. ");
            case "p" -> System.out.print("\nPaper vs. ");
            case "s" -> System.out.print("\nScissors vs. ");
        }
        
        switch(playerTwo){
            case "r" -> System.out.print("Rock");
            case "p" -> System.out.print("Paper");
            case "s" -> System.out.print("Scissors");
        }
    }
    
    static int[] playerWin(boolean playerOne) {
        int[] secret = new int[4];
        System.out.print((playerOne) ? "\nPlayer 1 wins! \n\nCongrats, Player 1!": "\nPlayer 2 wins! \n\nCongrats, Player 2!");
        System.out.print(" Now enter a 4-digit number (with spaces in between): ");
        Scanner read = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            secret[i] = read.nextInt();
        }
        System.out.println((playerOne) ? "\nTime to discover the hidden code. Good luck, Player 2!\nPlease put spaces in between the digits.": "\nTime to discover the hidden code. Good luck, Player 1!\nPlease put spaces in between the digits.");
        return secret;
    }
    
    static int hintCount(int[] secret) {
        int[] guess = new int[4];
        int[] sDigitCount = new int[10];
        int[] gDigitCount = new int[10];
        int bullCount = 0, cowCount = 0;
        
        Scanner read = new Scanner(System.in); 
        for (int i = 0; i < secret.length; i++) {
            guess[i] = read.nextInt();
                
            int g = guess[i];
            int s = secret[i];
                
            if (g == s){
                bullCount++;
            } else {
                sDigitCount[s]++;
                gDigitCount[g]++;
            }
        }
        for (int i = 0; i < 10; i++) {
            cowCount += Math.min(sDigitCount[i], gDigitCount[i]);
        }
        System.out.println("Bulls: " + bullCount);
        System.out.println("Cows: " + cowCount);
        return bullCount;
    }
}
