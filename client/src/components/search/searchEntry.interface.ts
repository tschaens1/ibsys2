export interface SearchEntry {
    id: number;
    name: string;
    route: string[];
    fragment?: string;
    tags: string[];
    description: string;
}