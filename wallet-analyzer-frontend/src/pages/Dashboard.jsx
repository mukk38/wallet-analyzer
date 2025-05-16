import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import AirdropTable from "../components/AirdropTable";
import FeeChart from "../components/FeeChart";
import TokenTransfersTable from "../components/TokenTransfersTable";
import TokenUsagePie from "../components/TokenUsagePie";
import AirdropBarChart from "../components/AirdropBarChart";

import ExportButton from "../components/ExportButton";

const Dashboard = () => {
    const { address } = useParams();
    const [airdrops, setAirdrops] = useState([]);
    const [fees, setFees] = useState([]);
    const [loading, setLoading] = useState(true);
    const [tokenTransfers, setTokenTransfers] = useState([]);
    const [topTokens, setTopTokens] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [airdropsRes, feesRes, transfersRes, topTokensRes] = await Promise.all([
                    axios.get(`http://localhost:8313/wallet/${address}/airdrops`),
                    axios.get(`http://localhost:8313/wallet/${address}/transactions`),
                    axios.get(`http://localhost:8313/wallet/${address}/token-transfers`),
                    axios.get(`http://localhost:8313/wallet/${address}/token-stats`)
                ]);
                setAirdrops(airdropsRes.data);
                setFees(feesRes.data);
                setTokenTransfers(transfersRes.data);
                setTopTokens(topTokensRes.data);
            } catch (error) {
                console.error("Veri alınamadı:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, [address]);

    if (loading) {
        return <div className="text-center mt-20">Yükleniyor...</div>;
    }

    return (
        <div id="dashboard">
            <ExportButton targetId="dashboard" />
            <div className="p-6 bg-gray-50 min-h-screen">
                <h1 className="text-2xl font-bold mb-4">Cüzdan: {address}</h1>

                <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 mb-6">
                    <h2 className="text-xl font-semibold mb-2">🎁 Airdrop Geçmişi</h2>
                    <AirdropTable data={airdrops} />
                </section>

                <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 mb-6">
                    <h2 className="text-xl font-semibold mb-2">⛽ Fee Zaman Grafiği</h2>
                    <FeeChart data={fees} />
                </section>
                <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 mb-6">
                    <h2 className="text-xl font-semibold mb-2">🔄 Token Transferleri</h2>
                    <TokenTransfersTable data={tokenTransfers} />
                </section>
                <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 mb-6">
                    <h2 className="text-xl font-semibold mb-2">🏆 En Çok Kullanılan Token’lar</h2>
                    <TokenUsagePie data={topTokens} />
                </section>
                <section className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 mb-6">
                    <h2 className="text-xl font-semibold mb-2">🎁 Airdrop Analizi</h2>
                    <AirdropBarChart data={airdrops} />
                </section>
            </div>
        </div >
    );
};

export default Dashboard;
