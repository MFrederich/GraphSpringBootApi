package com.study.graphSpringBootApi.exceptions;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.NonNull;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.graphql.execution.ErrorType.BAD_REQUEST;

@ControllerAdvice
public class ExceptionHandler {

    @GraphQlExceptionHandler
    public GraphQLError handleAccount(@NonNull AccountNotFoundException ex,@NonNull DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(BAD_REQUEST)
                .message("handler :" + ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleClient(@NonNull ClientNotFoundException ex,@NonNull DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(BAD_REQUEST)
                .message("handler :" + ex.getMessage())
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}
