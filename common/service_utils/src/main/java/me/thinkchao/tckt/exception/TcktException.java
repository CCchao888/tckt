package me.thinkchao.tckt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:chao
 * Date:2023-11-01
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TcktException extends RuntimeException{

    private Integer code;

    private String message;

}
