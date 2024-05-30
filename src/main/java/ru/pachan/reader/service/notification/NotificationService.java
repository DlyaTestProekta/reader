package ru.pachan.reader.service.notification;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import static ru.pachan.grpc.NotificationServiceGrpc.NotificationServiceImplBase;
import static ru.pachan.grpc.Reader.*;

@GrpcService
class NotificationService extends NotificationServiceImplBase {
    @Override
    public void findByPersonIdNotification(FindByPersonIdNotificationRequest request, StreamObserver<FindByPersonIdNotificationResponse> responseObserver) {
        // TODO get entity from db by personId
        FindByPersonIdNotificationResponse response = FindByPersonIdNotificationResponse.newBuilder().setNotification(
                Notification.newBuilder().setPersonId(request.getPersonId()).build() // TODO create entity to dto(proto by build())
        ).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findByIdNotification(FindByIdNotificationRequest request, StreamObserver<FindByIdNotificationResponse> responseObserver) {
        // TODO get entity from db by notificationId
        FindByIdNotificationResponse response = FindByIdNotificationResponse.newBuilder().setNotification(
                Notification.newBuilder().setPersonId(request.getNotificationId()).build() // TODO create entity to dto(proto by build())
        ).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}