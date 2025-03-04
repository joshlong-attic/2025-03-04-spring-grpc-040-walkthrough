package com.example.demo;

import com.example.demo.grpc.impl.AdoptionsGrpc;
import com.example.demo.grpc.impl.Dog;
import com.example.demo.grpc.impl.DogAdoptionRequest;
import com.example.demo.grpc.impl.DogsResponse;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@Service
class AdoptionsGrpcService extends AdoptionsGrpc.AdoptionsImplBase {

    @Override
    public void all(Empty request, StreamObserver<DogsResponse> responseObserver) {
        responseObserver
                .onNext(DogsResponse.newBuilder()
                .addAllDogs(List.of(
                        Dog.newBuilder().setName("dog1").setDescription("a goodest boy").setOwner("jlong").build(),
                        Dog.newBuilder().setName("dog2").setDescription("a goodest girl").setOwner("jlong").build()))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void adopt(DogAdoptionRequest request, StreamObserver<Empty> responseObserver) {
        System.out.println("adopting " +request.getName() + ' ' + request.getDogId());
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}