package com.eddicorp.application.controller;

import com.eddicorp.application.controller.post.CreatePostController;
import com.eddicorp.application.controller.user.CreateUserController;
import com.eddicorp.http.request.HttpMethod;
import com.eddicorp.http.request.HttpRequest;
import com.eddicorp.http.response.HttpResponse;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static com.eddicorp.utils.FileHandler.STATIC_FILE_PATH;

public class RootController implements Controller {
    private static final Map<RequestMapper, Controller> requestMap = new HashMap<>();
    private final Controller staticFileController = new StaticFileController();
    private final Controller notFoundController = new NotFoundController();

    public RootController() {
        requestMap.put(new RequestMapper("/post", HttpMethod.POST), new CreatePostController());
        requestMap.put(new RequestMapper("/users", HttpMethod.POST), new CreateUserController());
    }
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        final String uri = request.getUri();
        final RequestMapper requestMapper = new RequestMapper(uri, request.getHttpMethod());

        final Controller maybeController = requestMap.get(requestMapper);


        if (maybeController != null) {
            maybeController.handle(request, response);
            return;
        }

        // handle static files
        final String pathToLoad = Paths.get(STATIC_FILE_PATH, uri).toString();
        final URL maybeResource = this.getClass().getClassLoader().getResource(pathToLoad);
        if (maybeResource != null) {
            staticFileController.handle(request, response);
            return;
        }

        notFoundController.handle(request, response);
    }
}
