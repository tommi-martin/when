CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    open_from DATE NOT NULL,
    open_to DATE NOT NULL CHECK (open_to > open_from),
    author INTEGER NOT NULL,
    FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE event_users (
    event_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    PRIMARY KEY (event_id, user_id),
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE user_event_availability (
    event_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    available_from DATE NOT NULL,
    available_to DATE NOT NULL CHECK (available_to >= available_from),
    FOREIGN KEY (event_id) REFERENCES events(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
