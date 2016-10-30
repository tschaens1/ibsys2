export class Settings {
    public language: string;

    public stayLoggedIn: boolean;

    constructor(language: string, stayLoggedIn?: boolean) {
        this.language = language;
        this.stayLoggedIn = stayLoggedIn;
    }
}