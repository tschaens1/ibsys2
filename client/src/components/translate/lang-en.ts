import { SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION } from 'constants';
export const LANG_EN_NAME = 'en';

export const LANG_EN_TRANSLATIONS = {
    "modal": {
        "confirm": "Confirm",
        "cancel": "Cancel",
        "close": "Close",
        "title": {
            "help": "Help",
            "info": "Information",
        }
    },
    "login": {
        "username": "Username",
        "password": "Password",
        "stayLoggedIn": "Stay logged in",
        "button": {
            "login": "Login"
        },
    },
    "start": {
        "navbar": {
            "search": "Search",
            "advancedSearch": "Show all results for \"%0\"",
            "username": 'Hello %0',
            "settings": "Settings",
            "logout": "Logout"
        },
        "navigation": {
            "navigation": "Navigation",
            "dashboard": "Dashboard",
            "parts": "Parts overview",
            "planning": "Planning",
            "start": "Start",
            "warehouse": "Warehouse",
            "warehouse_statistics": "Warehouse statistics",
            "warehouse_settings": "Warehouse settings",
            "files": "Files",
            "upload": "Upload file",
            "downloads": "Download file",
            "other": "Other",
            "settings": "Settings",
            "help": "Help",
        },
    },
    "welcome": {
        "title": "Start",
        "description": "With the help of this tool you can plan your bike production",
        "actions": {
            "title": "Actions",
            "start_planning": "Start planning",
            "search_parts": "Search parts",
        },
        "links": {
            "title": "Links",
            "scsimulator": "SCSimulator",
            "test_environment": "Test environment",
        },
    },
    "settings": {
        "title": "Settings",
        "description": "Change the behaviour of the application here.",
        "language": {
            "title": "Language settings",
            "changeLanguage": "Here you can change the language:",
            "german": "German",
            "english": "English",
        },
    },
    "planning_material": {
        "title": "Material program",
        "description": "On this page you can plan the production.",
        "container": {
            "article": "Article",
            "description": "Description",
            "usage": "Usage",
            "quantity": "Quantity",
            "workstations": "Workstations",
        },
        "addArticle": {
            "title": "Add article",
            "help": "Enter a valid id for the production article. The following IDs can be inserted: [4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 26, 49, 54, 29, 50, 55, 30, 51, 56, 31, 1, 2, 3]",
            "addButton": "Add",
        },
        "help": {
            text: "You can drag & drop to reorder the items in the list below."
        },
        "validation": {
            "invalid_article_id": "Invalid article number",
        },
        "modal": {
            "addedArticle": "Artikel %0 has been added",
            "removedArticle": "Article %0 has been removed",
        },
    },
    "planning_production": {
        "title": "Production program",
        "description": "On this page you can plan the production.",
        "table": {
            "header_product": "Product",
            "header_bindingOrders": "Binding",
            "header_forecast_one": "Forecast + 1",
            "header_forecast_two": "Forecast + 2",
            "header_forecast_three": "Forecast + 3",
            "header_produce": "Produce",
            "header_warehouseStock": "Expected warehouse stock",
        },
    },
    "planning_overview": {
        "title": "Beginn planning",
        "description": "On this page you can prepare the planning.",

        "cancel": "Cancel",
        "continue": "Continue",
        "back": "Back",
        "quantity": "Quantity",
        "price": "Price",
        "yes": "Yes",

        "insert_period_to_plan": "1. Which period shall be planned?",
        "result_upload_last_period": "Upload results from the last period",
        "file_upload_text": "XML file",
        "reset_input_to_standard": "Reset input to standard",

        "sellwish": "Sellwish in period",
        "insert_sellwish": "2. What`s the sellwish?",
        "sellwish_product": "Product",
        "sellwish_forecast": "Forecast",

        "ask_for_direct_sale": "3. Are there direct sales?",
        "direct_sale_yes": "Yes",
        "direct_sale_no_back": "No, continue",
        "contract_penalty": "Contract penalty",
        "deativate_direct_sales": "Deactivate direct sales",

        "amount_planned_production": "4. What´s the planned production",

        "amount_savety_stock": "5. What´s the savety stock",
        "standard_security": "Standard security",
        "safety_stock_part": "Part",
        "safety_stock_quantity": "Amount",

        "show_single_items": "Show single parts",

        "send_data": "Send data",


        "server_is_calculating": "Server is calculating. This may take some seconds...",

        //toastr
        "toastr": {
            "wrong_period": "Wrong input for period",
            "missing_xml_document": "XML is missing",
            "wrong_sellwish": "Wrong sellwish",
            "very_high_sellwish": "Very high sellwish",
            "wrong_production_values": "Wrong inputs for production",
        },

        modals: {
            "cancelCalculation": {
                "title": "Cancel calculation",
                "text": "Do you really want to abort the calculation?"
            },
            "cancelPreparation": {
                "title": "Cancel planning",
                "text": "Do you really want to cancel the planning? Some of the inputs will be buffered, others must be inserted again."
            },
            "period": "Type in the number of the period which should be planned.",
            "xml": "Here you have to upload the results XML of the last period.",
            "sellwish": "The sellwish describes the amount of products that will be selled in the next period. Forecasts can change in the next periods.",
            "selldirect": "The selldirect describes the products that will be selled additionally to the sellwish. These products will be selled directly from the warehouse. Selldirects are optional.",
            "planned_production": "Here you have to specify the production of each product. Beware that these values have a big impact for the planning of the purchasing parts, the capacity planning and the production planning.",
            "safetyStock": "Here you can specify the safety stock for the warehouse. The safety stock describes how much parts you want to have in the warehouse additionally.",
        }
    },
    "planning_purchasing": {
        "title": "Purchase part disposition",
        "description": "On this page you can dispose the purchase parts.",
        "container": {
            "article": "Article",
            "description": "Description",
            "usage": "Usage",
            "quantity": "Quantity",
            "modus": "Modus",
            "workstations": "Workstations",
            "selectModus": {
                "normal": "Normal",
                "fast": "Fast",
                "jit": "JIT",
                "cheap_vendor": "Cheap order",
                "special_order": "Special order",
            }
        },
        "addArticle": {
            "title": "Add article",
            "help": "Enter a valid id for the production article. The following IDs can be inserted: [22, 23, 24, 25, 27, 32, 34, 36, 37, 38, 39, 40, 41, 42, 44, 46, 47, 48, 52, 53, 57, 58, 59]",
            "addButton": "Add",
        },
    },
    "planning_capacity": {
        "title": "Capacity planning",
        "table": {
            "workstation_number": "Workstation",
            "workload": "Workload",
            "capacity_requirements": "Capacity requirements",
            "shift": "Shifts",
            "overtime": "Overtime"
        },
    },
    "planning_inserts": {
        "title": "Input table",
        "description": "All entries are made here.",
        "directPurchase": "Direct sales",
        "purchaseOrders": "Purchasing orders",
        "productionOrders": "Production orders",
        "productionCapacity": "Production capacity",
        "bindingSales": "Binding sales",
        "componentNumber": "Nr.",
        "componentAmount": "Amount",
        "orderType": "Ordertype",
        "workplace": "Workplace",
        "workshift": "Workshifts",
        "overtime": "Overtime",
    },
    "planning": {
        "modals": {
            "exitPlanningText": "Do you really want to exit the planning? All inputs will be removed and you'll have to start a new planning.",
            "exitPlanningTitle": "Quit planning",
        },
        "toasts": {
            "savedXMLSuccessfully": "Saved XML successfully!",
            "errorWhileSavingXML": "Error while saving XML",
        },
    },
    "pagenotfound": {
        "title": "404 Error",
        "description": "Page could not be found.",
        "buttons": {
            "startpage": "Startpage"
        },
    },
    "help": {
        "title": "Help",
        "description": "Here you can find some tips to use the application",
        "lessons": {
            "get_help_header": "Find help",
            "get_help_title": "Where I get some help?",
            "get_help_message": "Click on the question mark right next to the text to get some help.",
            "plan_period_header": "Plan period",
            "prepare_planning_title": "Prepare planning",
            "prepare_planning_message": "You must do the following steps to begin the planning.",
            "search_parts_header": "Search parts",
            "search_parts": "Search for the desired part.",
            "change_language_header": "Change language",
            "change_language": "Navigate to the settings and change the language.",
        }
    },
    "search": {
        "title": "Search",
        "description": 'Search results for "%0". [%1 results found]',
        "noresultsmessage": "No results found.",
        "startpage": "Startpage",
    },
    "upload": {
        "title": "Upload XML",
        "description": 'Here you can upload the XML.',
    },
    "parts": {
        "title": "Parts",
        "description": "Overview over all parts of the bike.",
        "searchPlaceholder": "Filter the parts",
        "collection": {
            "usage": "Usage",
            "startAmount": "Start amount",
            "startPrice": "Start price",
            "costOfDelivery": "Cost of delivery",
            "timeOfDelivery": "Time of Delivery",
            "deviationOfDelivery": "Deviation of delivery",
            "requiredWorkStations": "Required work stations"
        },
        "nothingFound": "No parts found!",
    },
}