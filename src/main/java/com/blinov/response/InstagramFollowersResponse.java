package com.blinov.response;

public class InstagramFollowersResponse {

    public Data data;
    public String status;

    public Data getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public class Data {

        public User user;

        public User getUser() {
            return user;
        }
    }

    public class User {

        public EdgeFollowedBy edge_followed_by;

        public EdgeFollowedBy getEdge_followed_by() {
            return edge_followed_by;
        }
    }

    public class EdgeFollowedBy {

        public int count;
        public PageInfo page_info;
        public Edge[] edges;

        public int getCount() {
            return count;
        }

        public PageInfo getPage_info() {
            return page_info;
        }

        public Edge[] getEdges() {
            return edges;
        }
    }

    public class PageInfo {

        public boolean has_next_page;
        public String end_cursor;

        public boolean has_next_page() {
            return has_next_page;
        }

        public String getEnd_cursor() {
            return end_cursor;
        }
    }

    public static class Edge {

        public Node node;

        public Node getNode() {
            return node;
        }
    }

    public class Node {

        public String id;
        public String username;
        public String full_name;
        public String profile_pic_url;
        public boolean is_verified;
        public boolean followed_by_user;
        public boolean requested_by_viewer;

        public String getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getFull_name() {
            return full_name;
        }

        public String getProfile_pic_url() {
            return profile_pic_url;
        }

        public boolean isIs_verified() {
            return is_verified;
        }

        public boolean isFollowed_by_user() {
            return followed_by_user;
        }

        public boolean isRequested_by_viewer() {
            return requested_by_viewer;
        }
    }
}
