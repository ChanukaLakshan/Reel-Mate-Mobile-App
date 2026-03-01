import { createBrowserRouter } from "react-router";
import { LoginScreen } from "./screens/LoginScreen";
import { HomeScreen } from "./screens/HomeScreen";
import { MovieDetailsScreen } from "./screens/MovieDetailsScreen";
import { ReviewScreen } from "./screens/ReviewScreen";
import { ProfileScreen } from "./screens/ProfileScreen";
import { CreateListScreen } from "./screens/CreateListScreen";
import { ListSelectionScreen } from "./screens/ListSelectionScreen";
import { MyListsScreen } from "./screens/MyListsScreen";
import { ListDetailScreen } from "./screens/ListDetailScreen";

export const router = createBrowserRouter([
  {
    path: "/",
    Component: LoginScreen,
  },
  {
    path: "/home",
    Component: HomeScreen,
  },
  {
    path: "/movie/:id",
    Component: MovieDetailsScreen,
  },
  {
    path: "/review/:id",
    Component: ReviewScreen,
  },
  {
    path: "/profile",
    Component: ProfileScreen,
  },
  {
    path: "/create-list",
    Component: CreateListScreen,
  },
  {
    path: "/list-selection",
    Component: ListSelectionScreen,
  },
  {
    path: "/my-lists",
    Component: MyListsScreen,
  },
  {
    path: "/list-detail/:id",
    Component: ListDetailScreen,
  },
]);