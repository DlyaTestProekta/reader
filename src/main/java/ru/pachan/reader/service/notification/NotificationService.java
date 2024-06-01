package ru.pachan.reader.service.notification;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.http.HttpStatus;
import ru.pachan.reader.exception.data.RequestException;
import ru.pachan.reader.model.reader.Notification;
import ru.pachan.reader.repository.reader.NotificationRepository;

import static ru.pachan.grpc.NotificationServiceGrpc.NotificationServiceImplBase;
import static ru.pachan.grpc.Reader.*;
import static ru.pachan.reader.util.enums.ExceptionEnum.OBJECT_NOT_FOUND;

@GrpcService
@RequiredArgsConstructor
class NotificationService extends NotificationServiceImplBase {

    private final NotificationRepository repository;

    @Override
    public void findByPersonIdNotification(FindByPersonIdNotificationRequest request, StreamObserver<FindByPersonIdNotificationResponse> responseObserver) {

        Notification notification;
        try {
            notification = repository.findByPersonId(request.getPersonId()).orElseThrow(() ->
                    new RequestException(OBJECT_NOT_FOUND.getMessage(), HttpStatus.GONE));
            ru.pachan.grpc.Reader.Notification grpcNotification = ru.pachan.grpc.Reader.Notification.newBuilder()
                    .setNotificationId(notification.getId())
                    .setPersonId(notification.getPersonId())
                    .setCount(notification.getCount())
                    .build();
            FindByPersonIdNotificationResponse response = FindByPersonIdNotificationResponse.newBuilder()
                    .setNotification(grpcNotification)
                    .build();
            responseObserver.onNext(response);
        } catch (RequestException e) {
            ErrorInfo errorInfo = ErrorInfo.newBuilder()
                    .setReason(e.getMessage())
                    .putMetadata("message", e.getMessage())
                    .putMetadata("httpStatus", String.valueOf(e.getHttpStatus().value()))
                    .build();
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .addDetails(Any.pack(errorInfo))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
            return;
        }

        responseObserver.onCompleted();

    }

    @Override
    public void findByIdNotification(FindByIdNotificationRequest request, StreamObserver<FindByIdNotificationResponse> responseObserver) {

        Notification notification;
        try {
            notification = repository.findById(request.getNotificationId()).orElseThrow(() ->
                    new RequestException(OBJECT_NOT_FOUND.getMessage(), HttpStatus.GONE));
            ru.pachan.grpc.Reader.Notification grpcNotification = ru.pachan.grpc.Reader.Notification.newBuilder()
                    .setNotificationId(notification.getId())
                    .setPersonId(notification.getPersonId())
                    .setCount(notification.getCount())
                    .build();
            FindByIdNotificationResponse response = FindByIdNotificationResponse.newBuilder()
                    .setNotification(grpcNotification)
                    .build();
            responseObserver.onNext(response);
        } catch (RequestException e) {
            ErrorInfo errorInfo = ErrorInfo.newBuilder()
                    .setReason(e.getMessage())
                    .putMetadata("message", e.getMessage())
                    .putMetadata("httpStatus", String.valueOf(e.getHttpStatus().value()))
                    .build();
            Status status = Status.newBuilder()
                    .setCode(Code.NOT_FOUND.getNumber())
                    .setMessage(e.getMessage())
                    .addDetails(Any.pack(errorInfo))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
            return;
        }

        responseObserver.onCompleted();

    }

}