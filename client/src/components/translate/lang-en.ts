export const LANG_EN_NAME = 'en';

export const LANG_EN_TRANSLATIONS = {
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
            "warehouse": "Warehouse",
            "warehouse_statistics": "Warehouse statistics",
            "warehouse_settings": "Warehouse settings",
            "files": "Files",
            "upload": "Upload file",
            "downloads": "Download file"
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
        "table": {
            "header_bindingOrders": "Binding orders",
            "header_warehouseStockEnd": "Warehouse stock at the end of the period",
            "header_warehouseStockBeginning": "Warehouse stock at the beginning of the period",
            "header_tasksQueue": "Tasks in queue",
            "header_tasksProgress": "Task in Progross",
            "header_productionOrders": "Production orders",
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
        }
    },
    "planning_purchasing": {
        "title": "Purchase part disposition",
        "description": "On this page you can dispose the purchase parts.",
        "table": {
            "purchase_part": "Purchased item",
            "delivery_time": "Delivery time",
            "deviation": "Deviation",
            "discount_quantity": "Discount quantity",
            "stock_last_period": "Warehouse stock at the End of the period",
            "order_quantity": "Order quantity",
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
    "pagenotfound": {
        "title": "404 Error",
        "description": "Page could not be found.",
        "buttons": {
            "startpage": "Startpage"
        },
    },
    "help": {
        "title": "Help",
        "description": "Here you can find some tips to use the application"
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