package com.gymnastics.api;

import com.gymnastics.dto.request.RequestDraft;
import com.gymnastics.dto.request.RequestState;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
public class UserSession {

    private boolean requestActive = false;
    private RequestState requestState;
    private RequestDraft requestDraft;

    public void startRequest() {
        this.requestActive = true;
        this.requestState = RequestState.SELECT_BRANCH;
        this.requestDraft = new RequestDraft();
    }

    public void finishRequest() {
        this.requestActive = false;
        this.requestState = null;
        this.requestDraft = null;
    }
}
