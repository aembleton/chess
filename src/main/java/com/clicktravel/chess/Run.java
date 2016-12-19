package com.clicktravel.chess;

import com.clicktravel.chess.service.Decoder;

import java.util.List;

/**
 * Created by arthur on 19/12/16.
 */
public class Run {

    public static void main(String[] args) throws Exception{
        if (args.length < 1) {
            System.out.println("Please provide an encoded chess String");
        } else {
            List<String> decoded = Decoder.decode(args[0]);
            for (String row:decoded) {
                System.out.println(row);
            }
        }
    }
}
