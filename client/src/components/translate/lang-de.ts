export const LANG_DE_NAME = 'de';

export const LANG_DE_TRANSLATIONS = {
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
        "table": {
            "header_bindingOrders": "Verbindliche Aufträge",
            "header_warehouseStockEnd": "Lagerbestand am Ende der Periode",
            "header_warehouseStockBeginning": "Lagerbestand zu Beginn der Periode",
            "header_tasksQueue": "Aufträge in Warteschlange",
            "header_tasksProgress": "Aufträge in Bearbeitung",
            "header_productionOrders": "Produktionsaufträge",
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
            "overtime": "Überstunden"
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
            "period": "Geben Sie hier die Nummer der Periode ein, die Sie planen möchten.",
            "xml": "Laden Sie hier das Ergebnis XML der vergangenen Periode hoch."
        }
    },
    "planning_purchasing": {
        "title": "Kaufteildisposition",
        "description": "Auf dieser Seite können Sie die Kaufteile disponieren.",
        "table": {
            "purchase_part": "Kaufteil",
            "delivery_time": "Lieferfrist",
            "deviation": "Abweichung",
            "discount_quantity": "Diskontmenge",
            "stock_last_period": "Lagerbestand letzte Periode",
            "order_quantity": "Bestellmenge",
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
        "overtime": "Überstunden",
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