export const LANG_DE_NAME = 'de';

export const LANG_DE_TRANSLATIONS = {
    "modal": {
        "confirm": "Bestätigen",
        "cancel": "Abbrechen",
        "close": "Schließen",
        "title": {
            "help": "Hilfe",
            "info": "Information",
        }
    },
    "login": {
        "username": "Benutzername",
        "password": "Passwort",
        "stayLoggedIn": "Eingeloggt bleiben",
        "button": {
            "login": "Einloggen"
        },
    },
    "start": {
        "navbar": {
            "search": "Suche",
            "advancedSearch": "Alle Ergebnisse für \"%0\" anzeigen",
            "username": 'Hallo %0',
            "settings": "Einstellungen",
            "logout": "Ausloggen"
        },
        "navigation": {
            "navigation": "Navigation",
            "dashboard": "Dashboard",
            "parts": "Teileübersicht",
            "planning": "Planung",
            "warehouse": "Lager",
            "warehouse_statistics": "Lager Statistiken",
            "warehouse_settings": "Lager Einstellungen",
            "files": "Dateien",
            "upload": "Datei hochladen",
            "downloads": "Datei herunterladen"
        },
    },
    "settings": {
        "title": "Einstellungen",
        "description": "Auf dieser Seite können alle Einstellungen vorgenommen werden.",
        "language": {
            "title": "Spracheinstellungen",
            "changeLanguage": "Hier kann die Sprache verändert werden:",
            "german": "Deutsch",
            "english": "Englisch",
        },
    },
    "planning_material": {
        "title": "Materialplanung",
        "description": "Auf dieser Seite wird die Produktion geplant.",
        "container": {
            "article": "Artikel",
            "description": "Beschreibung",
            "usage": "Verwendung",
            "quantity": "Menge",
            "workstations": "Arbeitsplätze",
        },
        "addArticle": {
            "title": "Artikel hinzufügen",
            "help": "Geben Sie eine valide ID in das Eingabefeld ein und drücken Sie auf 'hinzufügen' um die Produktion weiter aufzuteilen. Folgende IDs können Sie einsetzen: [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 49, 54, 29, 50, 55, 30, 51, 56, 31, 1, 2, 3]",
            "addButton": "Hinzufügen",
        },
    },
    "planning_production": {
        "title": "Prouktionsplanung",
        "description": "Auf dieser Seite wird die Produktion geplant.",
        "table": {
            "header_product": "Produkt",
            "header_bindingOrders": "Verbindlich",
            "header_forecast_one": "Prognose + 1",
            "header_forecast_two": "Prognose + 2",
            "header_forecast_three": "Prognose + 3",
            "header_produce": "Produzieren",
            "header_warehouseStock": "Lagerbestand Ende der Planperiode",
        },
    },
    "planning_capacity": {
        "title": "Kapazitätsplanung",
        "table": {
            "workstation_number": "Arbeitsplatz",
            "workload": "Auslastung",
            "capacity_requirements": "Kapazitätsbedarf",
            "shift": "Schichten",
            "overtime": "Mehrarbeit [min/Tag]"
        },
    },
    "planning_overview": {
        "title": "Beginn Planung",
        "description": "Auf dieser Seite wird die Planung vorbereitet.",

        //
        "cancel": "Abbrechen",
        "continue": "Weiter",
        "back": "Zurück",
        "quantity": "Anzahl",
        "price": "Preis",
        "yes": "Ja",

        "insert_period_to_plan": "1. Welche Periode soll geplant werden?",
        "result_upload_last_period": "Upload der Ergebnisse aus der letzten Periode",
        "file_upload_text": "XML Datei",
        "reset_input_to_standard": "Eingaben auf Standard zurücksetzen",

        "insert_sellwish": "2. Wie ist der Vertriebswunsch?",
        "sellwish": "Vertriebswunsch in Periode",
        "sellwish_product": "Produkt",
        "sellwish_forecast": "Prognose",

        "ask_for_direct_sale": "3. Gibt es Direktverkäufe?",
        "direct_sale_yes": "Ja",
        "direct_sale_no_back": "Nein, Weiter",
        "contract_penalty": "Konventionalstrafe",
        "deativate_direct_sales": "Direktverkäufe deaktivieren",

        "amount_planned_production": "4. Wie hoch ist die geplante Produktion",

        "amount_savety_stock": "5. Wie hoch ist der Sicherheitsbestand?",
        "standard_security": "Standardsicherheit",
        "safety_stock_part": "Teil",
        "safety_stock_quantity": "Menge",

        "show_single_items": "Einzelteile zeigen",

        "send_data": "Daten absenden",

        "server_is_calculating": "Der Server wertet die Angaben aus. Bitte einen Moment warten...",

        //toastr
        "toastr": {
            "wrong_period": "Falsche Eingabe für Periode",
            "missing_xml_document": "XML Dokument fehlt",
            "wrong_sellwish": "Falscher Vertriebswunsch",
            "very_high_sellwish": "Sehr hoher Vertriebswunsch",
            "wrong_production_values": "Falsche Produktionseingaben",
        },

        modals: {
            "cancelCalculation": {
                "title": "Kalkulation abbrechen",
                "text": "Möchten Sie die Kalkulation der Eingaben wirklich abbrechen?"
            },
            "cancelPreparation": {
                "title": "Planung beenden",
                "text": "Möchten Sie diese Planung wirklich abbrechen? Einige Eingaben wurden zwischengespeichert, andere müssen neu eingegeben werden."
            },
            "period": "Geben Sie hier die Nummer der Periode ein, die Sie planen möchten.",
            "xml": "Laden Sie hier das Ergebnis XML der vergangenen Periode hoch.",
            "sellwish": "Der Vertriebswunsch gibt an, wie viele Produkte in der kommenden Periode verkauft werden sollen. Prognosen können sich noch ändern.",
            "selldirect": "Der Direktverkauf gibt an, welche Produkte zusätzlich zum Vertriebswunsch direkt ab Lager verkauft werden. Direktverkäufe sind optional.",
            "planned_production": "Geben Sie hier an, wie viele Produkte tatsächlich produziert werden sollen. Die Angabe dieser Werte hat einen großen Einfluss auf die Kalkulation der Kaufteile, der Eigenfertigung sowie auf die Kapazitätsplanung.",
            "safetyStock": "Hier kann der Sicherheitsbestand für das Lager angegeben werden. Der Sicherheitsbestand gibt an, wie viele Teile im Lager zur Sicherheit vorhanden sein sollen.",
        }
    },
    "planning_purchasing": {
        "title": "Kaufteildisposition",
        "description": "Auf dieser Seite können Sie die Kaufteile disponieren.",
        "container": {
            "article": "Artikel",
            "description": "Beschreibung",
            "usage": "Verwendung",
            "quantity": "Menge",
            "modus": "Modus",
            "workstations": "Arbeitsplätze",
            "selectModus": {
                "normal": "Normal",
                "fast": "Eil",
                "jit": "JIT",
                "cheap_vendor": "Billiganbieter",
                "special_order": "Sonderbestellung",
            }
        },
        "addArticle": {
            "title": "Artikel hinzufügen",
            "help": "Geben Sie eine valide ID in das Eingabefeld ein und drücken Sie auf 'hinzufügen'. Folgende IDs können Sie einsetzen: [22, 23, 24, 25, 27, 32, 34, 36, 37, 38, 39, 40, 41, 42, 44, 46, 47, 48, 52, 53, 57, 58, 59]",
            "addButton": "Hinzufügen",
        },
    },
    "planning_inserts": {
        "title": "Eingangstabelle",
        "description": "Hier werden alle Eingaben getätigt.",
        "directPurchase": "Direktverkauf",
        "purchaseOrders": "Einkaufsaufträge",
        "productionOrders": "Produktionsaufträge",
        "productionCapacity": "Produktionskapazität",
        "bindingSales": "Verbindliche Verkäufe",
        "componentNumber": "Nr.",
        "componentAmount": "Anzahl",
        "orderType": "Bestellart",
        "workplace": "Arbeitsplatz",
        "workshift": "Schichten",
        "overtime": "Mehrarbeit",
    },
    "planning": {
        "modals": {
            "exitPlanningText": "Wollen Sie die Planung wirklich beenden? Alle Eingaben werden zurückgesetzt und müssen erneut eingegeben werden.",
            "exitPlanningTitle": "Planung beenden",
        },
        "toasts": {
            "savedXMLSuccessfully": "XML gespeichert!",
            "errorWhileSavingXML": "Fehler beim Speichern",
        },
    },
    "pagenotfound": {
        "title": "404 Error",
        "description": "Die Seite konnte nicht gefunden werden.",
        "buttons": {
            "startpage": "Startseite"
        },
    },
    "help": {
        "title": "Hilfe",
        "description": "Hier finden Sie Anweisungen zur Bedienung des Tools"
    },
    "search": {
        "title": "Suche",
        "description": 'Suchergebnisse für "%0". [%1 Ergebnisse gefunden]',
        "noresultsmessage": "Keine Ergebnisse gefunden.",
        "startpage": "Startseite",
    },
    "upload": {
        "title": "XML hochladen",
        "description": 'Hier kann das XML hochgeladen werden.',
    },
    "parts": {
        "title": "Einzelteile",
        "description": "Hier ist eine Übersicht über alle Teile.",
        "searchPlaceholder": "Teile filtern",
        "collection": {
            "usage": "Verwendung",
            "startAmount": "Startmenge",
            "startPrice": "Startpreis",
            "costOfDelivery": "Lieferkosten",
            "timeOfDelivery": "Lieferzeit",
            "deviationOfDelivery": "Lieferabweichung",
            "requiredWorkStations": "Benötigte Arbeitsplätze"
        },
        "nothingFound": "Keine Teile gefunden!",
    },
}