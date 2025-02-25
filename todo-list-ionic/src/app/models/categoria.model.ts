import { Tarefa } from "./tarefa.model";

export interface Categoria {
    id: number;
    nome: string;
    tarefas: Tarefa[];
}